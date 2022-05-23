#!/bin/csh
setenv FREESURFER_HOME ~/freesurfer
foreach case (`cat ns_list_may2`)

~/freesurfer/bin/mri_convert -it analyze /media/data/APP/Images/Distortion_Corre
cted_Images/ImageOwl_output/{$case}_corrected.img -ot nii /media/data/APP/Projec
ts/FREESURFER_SUBJECTS/chg_files/{$case}_chg.nii

chg_data /media/data/APP/Projects/FREESURFER_SUBJECTS/chg_files/{$case}_chg.nii 
-swapNegAxes X Z -outname /media/data/APP/Projects/FREESURFER_SUBJECTS/chg_files
/{$case}_fs.nii

chg_data /media/data/APP/Projects/FREESURFER_SUBJECTS/chg_files/{$case}_fs.nii -
fliptb


