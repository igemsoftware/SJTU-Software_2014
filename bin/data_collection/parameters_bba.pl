#!/usr/bin/perl
use strict;

use LWP::Simple;
use HTML::Parse;
use HTML::Element;
use URI::URL;

my $address;
my $html;
my @info_bb;

my $first_char;
my @num;
my $i=0;
my $j=0;
my $length_num;
my $zeros;
my $part_id;
my $start=$ARGV[1];
my $end=$ARGV[2];
my $numlen=$ARGV[3];

my $loading;
my $error;
my $tot_bb;
my $no_line;
my $tot_lines;
my $is_category_end;
my $para_line;

open(O, ">".$ARGV[4]);
open(OE, ">".$ARGV[5]);
$first_char = $ARGV[0];


print O ("part_name\tname\tvalue\tunits\turl\tid\tm_date\tuser_id\tuser_name\n");
while($start <= $end){
	$length_num=length($start);
	$part_id=$start;
	$zeros=$numlen - $length_num;
	while($i<$zeros){
		$part_id="0".$part_id;
		$i++
	}
	$start++;
	$i=0;
	print "$part_id\t";
	$address="http://parts.igem.org/cgi/xml/part.cgi?part=BBa_".$first_char.$part_id;
	$html = get $address;
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
	
	$tot_lines=@info_bb;
	for ($no_line=0;$no_line<$tot_lines;$no_line++){
		if ($info_bb[$no_line] eq "<parameters>"){
			if ($info_bb[$no_line+2] =~ /\/parameters>$/){
				$para_line=$tot_lines;
				last;
			}
			if ($info_bb[$no_line+3] eq "<parameter>"){
				$para_line=$no_line+3;
				last;
			}
		}
	}
	for ($no_line=$para_line;$no_line<$tot_lines;$no_line++){
		if ($info_bb[$no_line] eq "<parameter>"){
			print O ("BBa_".$first_char.$part_id."\t");
			next;
		}
		if ($info_bb[$no_line] =~ /<name>(.*)<\/name>/){
			print O ("$1\t");
			next;
		}
		if ($info_bb[$no_line] =~ /<value>(.*)<\/value>/){
			print O ("$1\t");
			next;
		}
		if ($info_bb[$no_line] =~ /<units>(.*)<\/units>/){
			print O ("$1\t");
			next;
		}
		if ($info_bb[$no_line] =~ /<url>(.*)<\/url>/){
			print O ("$1\t");
			next;
		}
		if ($info_bb[$no_line] =~ /<id>(.*)<\/id>/){
			print O ("$1\t");
			next;
		}
		if ($info_bb[$no_line] =~ /<m_date>(.*)<\/m_date>/){
			print O ("$1\t");
			next;
		}
		if ($info_bb[$no_line] =~ /<user_id>(.*)<\/user_id>/){
			print O ("$1\t");
			next;
		}
		if ($info_bb[$no_line] =~ /<user_name>(.*)<\/user_name><\/parameter>$/){
			print O ("$1\n");
			next;
		}
		if ($info_bb[$no_line] =~ /<user_name>(.*)<\/user_name>.*<\/parameters>$/){
			print O ("$1\n");
			last;
		}
	}
}