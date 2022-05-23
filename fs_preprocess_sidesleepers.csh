#!/bin/csh

cd $SUBJECTS_DIR
foreach case (`cat ~/Desktop/freesurfer_runs/feb7`)

mksubjdirs $case
mri_convert /media/data/APP/Projects/FREESURFER_SUBJECTS/chg_files/"$case"_fs_ro
wflipped.nii /media/data/APP/Projects/FREESURFER_SUBJECTS/chg_files/"$case"_fs_r
owflippedTMP.mgz
mri_convert /media/data/APP/Projects/FREESURFER_SUBJECTS/chg_files/"$case"_fs_ro
wflippedTMP.mgz /media/data/APP/Projects/FREESURFER_SUBJECTS/chg_files/"$case"_f
s_rowflippedNEW.nii.gz
flirt -in /media/data/APP/Projects/FREESURFER_SUBJECTS/chg_files/"$case"_fs_rowf
lippedNEW.nii.gz -ref  /usr/share/fsl/5.0/data/standard/MNI152_T1_1mm.nii.gz -ou
t /media/data/APP/Projects/FREESURFER_SUBJECTS/chg_files/"$case"_fs_rowflipped_m
v.nii.gz -searchrx -180 180 -searchry -180 180 -searchrz -180 180 -interp sinc -
sincwindow hanning -dof 6
mri_convert -it nii /media/data/APP/Projects/FREESURFER_SUBJECTS/chg_files/"$cas
e"_fs_rowflipped_mv.nii.gz -ot mgz $SUBJECTS_DIR/$case/mri/orig/$case.mgz

cp /media/data2/APP_longitudinal/$case/mri/orig/$case.mgz /media/data2/APP_longi
tudinal/$case/mri/orig/001.mgz

end
