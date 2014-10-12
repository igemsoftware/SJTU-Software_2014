#!/usr/bin/perl
use strict;
use warnings;

use LWP::Simple;
use HTML::Parse;
use HTML::Element;
use URI::URL;

my $address;
my $html;

my @info;
my $tot_lines;
my $no_line;

my $confirmed;
my $not_confirmed;
my $detail_not_confirmed;

my $tot_comments;
my $tot_stars;
my @stars;
my $avg_stars;

open(I, $ARGV[0]);
#open(OT, ">".$ARGV[2]);
open(O, ">".$ARGV[1]);

print O ("part_name\tDNA_Status\tGroup_Favorite\tDelete_This_Part\ttot_Confirmed\tDetail_not_confirmed\tAverage_Stars\tTot_Comments\n");

while (<I>) {
	$address="http://parts.igem.org/cgi/partsdb/part_info.cgi?part_name=".$_;
	$html = get $address;
	print $_;
	#print OT $html;

	chomp;
	print O ("$_");

	@info = split(/\n/,$html);
	
	$tot_lines = @info;
	for ($no_line = 0; $no_line <= ($tot_lines - 1) ; $no_line++) {
		if ($info[$no_line] =~ /DNA Status<TD>(.*)<TD>/) {
			print O ("$1\t");
		}
		elsif ($info[$no_line] =~ /Favorite<TD.*>(.*)<TD/) {
			print O ("$1\t");
		}
		elsif ($info[$no_line] =~ /Delete This.*<TD.*>(.*)<TD/) {
			print O ("$1\t");
		}
	}

	$confirmed = 0;
	$not_confirmed = 0;
	$detail_not_confirmed = "";

	$address = "http://parts.igem.org/partsdb/get_part.cgi?part=".$_;
	$html = get $address;
	#print OT $html;

	@info = split(/\n/,$html);
	
	$tot_lines = @info;
	for ($no_line = 0; $no_line <= ($tot_lines - 1) ; $no_line++) {
		if ($info[$no_line] =~ /Distribution<TD.*>(Confirmed)<\/span>/) {
				$confirmed++;
		}
		elsif ($info[$no_line] =~ /Distribution<TD.*>(.*)<\/span>/) {
			$detail_not_confirmed = $detail_not_confirmed.$1."/";
			$not_confirmed++;
		}
		elsif ($info[$no_line] =~ /pSB.*<TD>(.*)$/) {
			$detail_not_confirmed = $detail_not_confirmed.$1."/";
			$not_confirmed++;
		}
	}

	if (($confirmed + $not_confirmed) == 0) {
		print O ("NULL\tNULL\t");
	}
	else {
		print O ($confirmed."\t$detail_not_confirmed\t");
	}

	$_ =~ tr/\t//d;

	$address = "http://parts.igem.org/Part:".$_.":Experience";
	$html = get $address;
	#print OT $html;


	@info = split(/\n/,$html);
	$tot_lines = @info;

	$tot_comments = 0;
	$tot_stars = 0;
	for ($no_line = 0; $no_line <= ($tot_lines - 1) ; $no_line++) {
		if ($info[$no_line] =~ /User_Reviews/) {
			last;
		}
	}

	for (; ($info[$no_line] !~ /^<\!--/) && ($no_line <= ($tot_lines - 1)); $no_line ++) {
		if ($info[$no_line] =~ /^<table width=\"80\%\" style=\"border:1px solid gray\">/) {
			$tot_comments++;
			#print ("$no_line\n");
		}
		elsif ($info[$no_line] =~ /^<div style.*>(.*)<\/div>$/) {
			@stars = split(/;/,$1);
			$tot_stars += @stars;
		}
	}

	if ($tot_comments == 0) {
		print O ("No Stars\tNo Comments\n");
	}
	else {
		$avg_stars = $tot_stars / $tot_comments;
		print O ("$avg_stars\t$tot_comments\n");
	}

}

close(I);
close(O);
#close(OT);