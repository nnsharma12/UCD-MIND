
cd /Volumes/data/data2/APP_longitudinal/pairedFromT1andT3



ID_list_t1 =['101314-200';'104622-200';'104803-100';'105097-100';'105843-201';'106802-100';'106826-100';'106970-100';'106972-100';'106993-100';'106994-100';'107183-100';'107201-200';'107205-100';'107320-100';'107472-100';'107560-100';'107561-100';'107671-100';'107674-201';'107694-100';'107705-100';'107738-100';'107783-100';'107802-100';'107841-100';'107907-100';'107935-100';'107937-100';'107990-100';'108130-100';'108229-100';'108246-100';'108270-100';'108297-100';'108366-100';'108367-100';'108500-100';'108567-100';'108590-100';'108598-100';'108644-100';'108674-100';'108676-100';'108678-100';'108693-100';'108706-100';'108738-100';'108920-100';'108970-100';'109009-100';'109054-100';'109087-100';'109092-100';'109124-100';'109134-100';'109138-100';'109156-100';'109295-100';'109355-100';'109441-200';'109455-100';'109493-100';'109543-100';'109556-100';'109595-100';'109651-100';'109709-100';'109724-100';'109771-100';'109776-100';'109783-100';'109810-100';'109858-100';'109868-100';'109937-100';'109956-100';'109958-100';'109975-100';'110021-100';'110023-100';'110052-100';'110114-100';'110116-100'];
DeltaAge=[25.1,26.1,25.8,26.6,29.9,26.7,27.1,26.3,27.4,26.1,24.8,25.1,27.6,26.5,23.7,28.3,31.3,27.6,24.3,25.3,31.4,26.6,27.6,30.5,30.3,27.2,31.3,28,30.2,29.2,28.4,26.9,28.4,29.6,27,26.8,25.7,29.8,25.6,25.3,26.4,26.7,28,26.7,27.1,28.1,26.3,26.5,32.5,26.2,28.6,27.5,27.2,26.4,27.9,28.3,25.8,22.7,24.3,26.1,23.7,26.8,25,26,28.9,26.5,24.5,25.2,28.3,24.2,24,24.1,25.1,26.3,25.1,29.4,26.6,26,23.6,25.1,28.3,27,25.2,28.1];

% ID at t2 = ID at t1 and _2 at the end



for k=1:size(ID_list_t1,1)
	[lgi_subj_t1,M]=load_mgh(strcat('/Volumes/data/data2/APP_longitudinal/',ID_list_t1(k,:),'/surf/lh.volume.fwhm10.fsaverage.mgh'));
	[lgi_subj_t2,M]=load_mgh(strcat('/Volumes/data/data2/APP_longitudinal/',ID_list_t1(k,:),'_2/surf/lh.volume.fwhm10.fsaverage.mgh'));
    
    %mkdir(strcat(ID_list_t1(k,:),'_pairedFromT1andT3/surf')) 
 
    lh_avg_subj=mean([lgi_subj_t1,lgi_subj_t2],2);
    save_mgh(lh_avg_subj,strcat(ID_list_t1(k,:),'_pairedFromT1andT3/surf/lh.avg.volume.fwhm10.fsaverage.mgh'),M)
    
    lh_delta_subj=lgi_subj_t2-lgi_subj_t1;
    save_mgh(lh_delta_subj,strcat(ID_list_t1(k,:),'_pairedFromT1andT3/surf/lh.delta.volume.fwhm10.fsaverage.mgh'),M) 
   
    lh_rate_subj=lh_delta_subj/DeltaAge(k);
    save_mgh(lh_rate_subj,strcat(ID_list_t1(k,:),'_pairedFromT1andT3/surf/lh.rate.volume.fwhm10.fsaverage.mgh'),M)
 

    clear lgi_subj_t1 lgi_subj_t2 lh_avg_subj lh_delta_subj lh_rate_subj 


    [lgi_subj_t1,M]=load_mgh(strcat('/Volumes/data/data2/APP_longitudinal/',ID_list_t1(k,:),'/surf/rh.volume.fwhm10.fsaverage.mgh'));
	[lgi_subj_t2,M]=load_mgh(strcat('/Volumes/data/data2/APP_longitudinal/',ID_list_t1(k,:),'_2/surf/rh.volume.fwhm10.fsaverage.mgh'));
   
 
    rh_avg_subj=mean([lgi_subj_t1,lgi_subj_t2],2);
    save_mgh(rh_avg_subj,strcat(ID_list_t1(k,:),'_pairedFromT1andT3/surf/rh.avg.volume.fwhm10.fsaverage.mgh'),M)
    
    rh_delta_subj=lgi_subj_t2-lgi_subj_t1;
    save_mgh(rh_delta_subj,strcat(ID_list_t1(k,:),'_pairedFromT1andT3/surf/rh.delta.volume.fwhm10.fsaverage.mgh'),M)
   
    rh_rate_subj=rh_delta_subj/DeltaAge(k);
    save_mgh(rh_rate_subj,strcat(ID_list_t1(k,:),'_pairedFromT1andT3/surf/rh.rate.volume.fwhm10.fsaverage.mgh'),M)
   

     clear lgi_subj_t1 lgi_subj_t2 rh_avg_subj rh_delta_subj rh_rate_subj 

end

