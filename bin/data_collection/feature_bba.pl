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
my $ininin;

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
my $part_name;

open(O, ">".$ARGV[4]);
open(OE, ">".$ARGV[5]);
print O ("part_name\tid\ttitle\ttype\tdirection\tstartpos\tendpos");

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
				#print $html;

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
				for ($no_line = 0; $no_line <= ($tot_lines-1); $no_line++) {
					if (($info_bb[$no_line] =~ /_sub.*></)) {
						next;
					}
					elsif ($info_bb[$no_line] =~ /^<subpart>$/) {
						$no_line = $no_line + 5;
					}
					elsif ($info_bb[$no_line] =~ /<part_name>(.*)<\/part_name>/) {
						$part_name = $1;
					}
					elsif ($info_bb[$no_line] =~ /<features><\/features>/) {
						last;
					}
					elsif ($info_bb[$no_line] =~ /^<feature>$/) {
						print O ("\n");
						print O ("$part_name\t");
					}
					elsif ($info_bb[$no_line] =~ /<id>(.*)<\/id>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<title>(.*)<\/title>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<type>(.*)<\/type>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<direction>(.*)<\/direction>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<startpos>(.*)<\/startpos>/) {
						print O ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<endpos>(.*)<\/endpos><\/feature>$/) {
						print O ("$1");
					}
					elsif ($info_bb[$no_line] =~ /<endpos>(.*)<\/endpos><\/feature><\/features>$/) {
						print O ("$1");
						last;
					}

				}
}

close(O);
close(OE);

print("\n\n");
print("e$error\tl$loading\n");