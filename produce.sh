echo enter file name:

read varname 

echo converting $varname

echo specify path

read path 

cd $path 

pandoc -o $varname.pdf $varname.md




