#!/usr/bin/perl
use strict;
use warnings;

use LWP::Simple;
use HTML::Parse;
use HTML::Element;
use URI::URL;

my $first_char;
my $length_num;
my $part_id;
my $zeros;

my $i;
my $j;
my $loading;
my $error;
my $tot_bb;

my $address;
my $html;
my @info_bb;
my $tot_lines;
my $no_line;

my $part_name;

open(I, $ARGV[0]);
open(DEEP, ">".$ARGV[1]);
open(SPEC, ">".$ARGV[2]);
open(SCAR, ">".$ARGV[3]);
open(CAT, ">".$ARGV[4]);
open(TWIN, ">".$ARGV[5]);
open(OE, ">".$ARGV[6]);

print DEEP ("part_name\tsubpart_id\tsubpart_name\tsubpart_short_desc\tsubpart_type\tsubpart_nickname\n");
print SPEC ("part_name\tsubpart_id\tsubpart_name\tsubpart_short_desc\tsubpart_type\tsubpart_nickname\n");
print SCAR ("part_name\tscar_id\tscar_standard\tscar_type\tscar_name\tscar_nickname\tscar_comments\tscar_sequence\n");
print CAT ("part_name\tcategory\n");
print TWIN ("part_name\ttwin\n");

$loading = 0;
$error = 0;

while (<I>) {
		
		$_ =~ tr/\t //d;
		$address="http://parts.igem.org/cgi/xml/part.cgi?part=".$_;
				$html = get $address;
				#print $html;

				@info_bb = split(/\n/,$html);
				if ($info_bb[5] =~ /^<ERROR>/) {
					$error++;
					$tot_bb = $error + $loading;
					print OE ("$_\n");
					print ("e$error\tt$tot_bb\n");
					next;
				}

				$loading++;
				$tot_bb = $error + $loading;
				print("l$loading\tt$tot_bb\n");
				
				$tot_lines = @info_bb;
				for ($no_line = 0; $no_line <= ($tot_lines-1); $no_line++) {
					if ($info_bb[$no_line] =~ /<part_name>(.*)<\/part_name>/) {
						$part_name = $1;
						last;
					}
				}

				for ($no_line += 12 ;$no_line <= ($tot_lines-1); $no_line++) {
					if ($info_bb[$no_line] =~ /<deep_subparts><\/deep_subparts>/) {
						last;
					}
					elsif ($info_bb[$no_line] =~ /^<subpart>$/) {
						print DEEP ("$part_name\t");
					}
					elsif ($info_bb[$no_line] =~ /<part_id>(.*)<\/part_id>/) {
						print DEEP ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<part_name>(.*)<\/part_name>/) {
						print DEEP ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<part_short_desc>(.*)<\/part_short_desc>/) {
						print DEEP ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<part_type>(.*)<\/part_type>/) {
						print DEEP ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<part_nickname>(.*)<\/part_nickname><\/subpart>$/) {
						print DEEP ("$1\n");
					}
					elsif ($info_bb[$no_line] =~ /<part_nickname>(.*)<\/part_nickname>.*<\/deep_subparts>$/) {
						print DEEP ("$1\n");
						last;
					}
				}

				for ( ; $no_line <= ($tot_lines-1); $no_line++) {
					if ($info_bb[$no_line] =~ /<specified_subparts><\/specified_subparts>/) {
						last;
					}
					elsif ($info_bb[$no_line] =~ /^<subpart>$/) {
						print SPEC ("$part_name\t");
					}
					elsif ($info_bb[$no_line] =~ /<part_id>(.*)<\/part_id>/) {
						print SPEC ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<part_name>(.*)<\/part_name>/) {
						print SPEC ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<part_short_desc>(.*)<\/part_short_desc>/) {
						print SPEC ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<part_type>(.*)<\/part_type>/) {
						print SPEC ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<part_nickname>(.*)<\/part_nickname><\/subpart>$/) {
						print SPEC ("$1\n");
					}
					elsif ($info_bb[$no_line] =~ /<part_nickname>(.*)<\/part_nickname>.*<\/specified_subparts>$/) {
						print SPEC ("$1\n");
						last;
					}
				}

				for ( ; $no_line <= ($tot_lines-1); $no_line++) {
					if ($info_bb[$no_line] =~ /<specified_subscars><\/specified_subscars>/) {
						last;
					}
					elsif ($info_bb[$no_line] =~ /^<scar>$/) {
						print SCAR ("$part_name\t");
					}
					elsif ($info_bb[$no_line] =~ /<scar_id>(.*)<\/scar_id>/) {
						print SCAR ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<scar_standard>(.*)<\/scar_standard>/) {
						print SCAR ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<scar_type>(.*)<\/scar_type>/) {
						print SCAR ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<scar_name>(.*)<\/scar_name>/) {
						print SCAR ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<scar_nickname>(.*)<\/scar_nickname>/) {
						print SCAR ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<scar_comments>(.*)<\/scar_comments>/) {
						print SCAR ("$1\t");
					}
					elsif ($info_bb[$no_line] =~ /<scar_sequence>(.*)<\/scar_sequence><\/scar>$/) {
						print SCAR ("$1\n");
					}
					elsif ($info_bb[$no_line] =~ /<\/subpart><\/specified_subscars>$/) {
						last;
					}
				}

				for ( ; $no_line <= ($tot_lines-1); $no_line++) {
					if ($info_bb[$no_line] =~ /<categories><\/categories>/) {
						last;
					}
					elsif ($info_bb[$no_line] =~ /<category>(.*)<\/category>$/) {
						print CAT ("$part_name\t");
						print CAT ("$1\n");
					}
					elsif ($info_bb[$no_line] =~ /<category>(.*)<\/category><\/categories>$/) {
						print CAT ("$part_name\t");
						print CAT ("$1\n");
						last;
					}
				}

				for ( ; $no_line <= ($tot_lines-1); $no_line++) {
					if ($info_bb[$no_line] =~ /<twins><\/twins>/) {
						last;
					}
					elsif ($info_bb[$no_line] =~ /<twin>(.*)<\/twin>$/) {
						print TWIN ("$part_name\t");
						print TWIN ("$1\n");
					}
					elsif ($info_bb[$no_line] =~ /<twin>(.*)<\/twin><\/twins>$/) {
						print TWIN ("$part_name\t");
						print TWIN ("$1\n");
						last;
					}
				}
}

close(DEEP);
close(SPEC);
close(SCAR);
close(CAT);
close(TWIN);
close(OE);
close(I);