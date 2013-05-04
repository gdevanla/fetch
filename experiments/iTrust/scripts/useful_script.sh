

#Change files names of tests produced.
for i in `ls`; do mv $i Test${i#edu.*Test}; done;
