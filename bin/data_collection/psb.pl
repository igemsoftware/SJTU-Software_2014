#!/usr/bin/perl
use strict;
use warnings;

use LWP::Simple;
use HTML::Parse;
use HTML::Element;
use URI::URL;

my @mid_char;
my $num_1;
my $num_2;
my $letters;
my $num_lets;

my $i;
my $j;
my $k;
my $m;
my @mid_lets;
my $tot_lets;

my $address;
my $html;

my @info_bb;
my $error;
my $loading;
my $tot_bb;
my $tot_lines;
my $no_line;

open(O, ">".$ARGV[1]);
open(OE, ">".$ARGV[2]);
print O ("part_id\tpart_name\tpart_short_name\tpart_short_desc\tpart_type\trelease_status\tsample_status\tpart_results\tpart_nickname\tpart_rating\tpart_url\tpart_entered\tpart_author\tsequences\tsamples\treferences\tgroups\n");

$num_lets = $ARGV[0];

$tot_lets = 13 ** $num_lets;

for ($i = 0; $i < $tot_lets ; $i++ ) {
	$mid_lets[$i] = "";
}

$mid_char[0]="A";
$mid_char[1]="C";
$mid_char[2]="E";
$mid_char[3]="G";
$mid_char[4]="K";
$mid_char[5]="N";
$mid_char[6]="Na";
$mid_char[7]="R";
$mid_char[8]="S";
$mid_char[9]="St";
$mid_char[10]="T";
$mid_char[11]="Tm";
$mid_char[12]="Z";

$k = 0;
for ($i = 0; $i <= 12 ; $i++) {
	for ($j = 0; $j <= 12; $j++) {
		for ($m = 0; $m <= 12 ;$m++) {
		
			$mid_lets[$k] = $mid_lets[$k].$mid_char[$i].$mid_char[$j].$mid_char[$m];
			$k++;
		}
	}
}

$error = 0;
$loading = 0;
for ($num_1 = 1; $num_1 <= 9 ; $num_1++) {
	for ($num_2 = 23; $num_2 <= 29 ; $num_2++) {

		for ($k = 0; $k < $tot_lets ; $k++) {
			
			$address="http://parts.igem.org/cgi/xml/part.cgi?part=pSB".$num_1.$mid_lets[$k].$num_2;
			$html = get $address;

			@info_bb = split(/\n/,$html);
			if ($info_bb[5] =~ /^<ERROR>/) {
				$error++;
				$tot_bb = $error + $loading;
				print OE ("$num_1$mid_lets[$k]$num_2\n");
				print ("e$error\tt$tot_bb\n");
				next;
			}
			
			$loading++;
			$tot_bb = $error + $loading;
			print("l$loading\tt$tot_bb\n");
				
			$tot_lines = @info_bb;

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
	}
}

close(O);
close(OE);
