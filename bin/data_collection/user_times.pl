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
my $i;
my $used;

open(I, $ARGV[0]);
open(O, ">".$ARGV[1]);

while (<I>) {

	$used = 0;
	$address="http://parts.igem.org/Part:".$_;
	$html = get $address;
	chomp;
	print O ("$_\t");
	print ("$_\n");

	#print O $html;
	@info = split(/\n/,$html);

	$tot_lines = @info;
	for ($i = 0 ; $i <= ($tot_lines - 1); $i++ ) {
		if ($info[$i] =~ />(\d*) Uses/) {
			print O ("$1\n");
			$used = 1;
			last;
		}
	}
	
	if ($used == 0) {
		print O ("0\n");
	}
}

close(I);
close(O);