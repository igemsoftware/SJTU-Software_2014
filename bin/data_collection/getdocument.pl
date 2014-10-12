#!/usr/bin/perl
use strict;

use LWP::Simple;
use HTML::Parse;
use HTML::Element;
use URI::URL;

open (file,$ARGV[0]);
open (out,">",$ARGV[1]);


my $address_main;
my $address_experience;
my $address_design;
my $html_main;
my $html_experience;
my $html_design;
my $part_name;
my @count;
my @tmp;
my $i;
my $len;

print out ("part_name\tcount_main\tcount_design\tcount_experience\tcount_total\n");
while ($part_name = <file>){
	chomp($part_name);
	$address_main = "http://parts.igem.org/Part:".$part_name;
	$address_experience = "http://parts.igem.org/Part:".$part_name.":Experience";
	$address_design = "http://parts.igem.org/Part:".$part_name.":Design";
	$html_main = get $address_main;
	$html_experience = get $address_experience;
	$html_design = get $address_design;
	#count
	$count[0] = length($html_main);
	$count[1] = length($html_design);
	$count[2] = length($html_experience);
	$count[3] = $count[0]+$count[1]+$count[2];
	print out ("$part_name\t$count[0]\t$count[1]\t$count[2]\t$count[3]\n");
	$count[0] = 0;
	$count[1] = 0;
	$count[2] = 0;
	$count[3] = 0;
	#print "$part_name\n";
}

close file;
close out;