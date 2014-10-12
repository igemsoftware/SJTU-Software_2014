#!/usr/bin/perl
use strict;
use warnings;

use LWP::Simple;
use HTML::Parse;
use HTML::Element;
use URI::URL;

my $address;
my @line;
my $html;

my @info_bb;
my $tot_lines;
my $no_line;

my $seq;

open(I, $ARGV[0]);
open(O, ">".$ARGV[1]);

while (<I>) {
	@line = split("\t", $_);
	print ("$line[0]\n");

	$address="http://parts.igem.org/cgi/xml/part.cgi?part=".$line[0];
	$html = get $address;
	#print O $html;

	@info_bb = split(/\n/,$html);
	$tot_lines = @info_bb;
	
	$seq = "";
	for ($no_line = 0; $no_line <= ($tot_lines-1); $no_line++) {
		
		if ($info_bb[$no_line] =~ /<seq_data><\/seq/) {
			$no_line = $tot_lines;
			last;
		}
		elsif ($info_bb[$no_line] =~ /<seq_data>(.*)/) {
			$seq = $seq.$1;
			last;
		}	
	}
	for ($no_line++; $no_line <= ($tot_lines-1); $no_line++) {
		if ($info_bb[$no_line] !~ /^<\/seq_data>/) {
			$seq = $seq.$info_bb[$no_line];
		}
		elsif ($info_bb[$no_line] =~ /^<\/seq_data>/) {
			last;
		}
	}
	print O ("$line[0]\t$seq\n");
}

close(I);
close(O);
