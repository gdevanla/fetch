
#replace filename genereated while doing wget
for i in `ls`; do x=`echo $i | sed "s/doku.php.*s:uc\([1-9]*\).*/uc\1/g"`; mv $i $x.txt ; done;

#just get the text area
for i in `ls uc*`;do `sed -i .bckp -e '/textarea/,/textarea/!d' $i` ; done;

# to strip all unwanted content
for i in `ls uc*.txt`; do sed -i .bckp2 -e 's/<input.*">//g ; s/[\*\|]//g ; s/\==//g ; s/^[0-9\^].*//g ; s/\[[SE][0-9]*\]//g' $i; done
