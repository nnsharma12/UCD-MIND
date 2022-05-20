#! /bin/csh

foreach CASE ( `cat DWI_cases_image_path` )
cd `echo $CASE | cut -d/ -f1-3`
dtifit -k `echo $CASE | cut -d/ -f4` -o {`echo $CASE | cut -d/ -f1`}_dtifit -m mask -r bvecs -b bvals
cd ../../../
end 


