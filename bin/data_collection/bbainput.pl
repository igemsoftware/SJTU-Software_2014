#!/usr/bin/perl
use strict;
use warnings;

use LWP::Simple;
use HTML::Parse;
use HTML::Element;
use URI::URL;

my $address;
my $html;
my @info_bb;

my $first_char;
my @num;

my $i;
my $j;
my $length_num;
my $zeros;
my $part_id;

my $loading;
my $error;
my $tot_bb;
my $no_line;
my $tot_lines;
my $is_category_end;

open(O, ">".$ARGV[4]);
open(OE, ">".$ARGV[5]);
print O ("part_id\tpart_name\tpart_short_name\tpart_short_desc\tpart_type\trelease_status\tsample_status\tpart_results\tpart_nickname\tpart_rating\tpart_url\tpart_entered\tpart_author\tsequences\tsamples\treferences\tgroups\n");

$first_char = $ARGV[0];

for ($i=0; $i <= 6 ; $i++) {
	$num[$i] = "0";
}

$loading = 0;
$error = 0;


for ($i=$ARGV[1]; $i <= $ARGV[2]; $i++) {
	$length_num = length($i);
	$part_id = $i;
	$zeros = $ARGV[3] - $length_num;
	for ($j = 1; $j<= $zeros ;$j++) {
		$part_id = "0".$part_id;
	}

				$address="http://parts.igem.org/cgi/xml/part.cgi?part=BBa_".$first_char.$part_id;
				$html = get $address;
				print $html;

				@info_bb = split(/\n/,$html);
				if ($info_bb[5] =~ /^<ERROR>/) {
					$error++;
					$tot_bb = $error + $loading;
					print OE ("$part_id\n");
					print ("e$error\tt$tot_bb\n");
					next;
				}

				$loading++;
				$tot_bb = $error + $loading;
				print("l$loading\tt$tot_bb\n");
				
				$tot_lines = @info_bb;
				$is_category_end = 0;
				for ($no_line = 0; $no_line <= ($tot_lines-1); $no_line++) {
					if (($info_bb[$no_line] =~ /_sub.*></)) {
						next;
					}
					elsif ($info_bb[$no_line] =~ /^<subpart>$/) {
						$no_line = $no_line + 5;
					}
					elsif (($info_bb[$no_line] =~ /<part_id></) || ($info_bb[$no_line] =~ /<part_name></) || ($info_bb[$no_line] =~ /desc></) || ($info_bb[$no_line] =~ /_type></) || ($info_bb[$no_line] =~ /status></) || ($info_bb[$no_line] =~ /results></) || ($info_bb[$no_line] =~ /nickname></) || ($info_bb[$no_line] =~ /rating></) || ($info_bb[$no_line] =~ /_url></) || ($info_bb[$no_line] =~ /entered></) || ($info_bb[$no_line] =~ /thor></) || ($info_bb[$no_line] =~ /<seq_data></)) {
						#print ("$info_bb[$no_line]\n");
						print O ("//\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_id>(.*)<\/part_id>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_name>(.*)<\/part_name>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_short_name>(.*)<\/part_short_name>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_short_desc>(.*)<\/part_short_desc>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_type>(.*)<\/part_type>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<release_status>(.*)<\/release_status>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<sample_status>(.*)<\/sample_status>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_results>(.*)<\/part_results>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_nickname>(.*)<\/part_nickname>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_rating>(.*)<\/part_rating>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_url>(.*)<\/part_url>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_entered>(.*)<\/part_entered>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<part_author>(.*)<\/part_author>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /^<seq_data>(.*)/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /(.*)<\/samples>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /(.*)<\/references>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /(.*)<\/groups>/) {
						print O ("$1\t");
					}
									
				}
				print O ("\n");
}

close(O);
close(OE);

print("\n\n");
print("e$error\tl$loading\n");
