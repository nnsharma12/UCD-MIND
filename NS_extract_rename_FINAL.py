import os
import zipfile


key1 = "atlas_list.txt"
key2 = "_masked.hdr"
key3 = ".zip"
# atlas = ""
dir_name = '/Users/natasha/Desktop/ntest'
extension = ".zip"


print(f"Current Directory: {os.getcwd()}")
os.chdir(dir_name) # change directory from working dir to dir with files


print("------------------")
print("Extracting Files")
print("------------------")
for item in os.listdir(dir_name): # loop through items in dir
    if item.endswith(extension): # check for ".zip" extension
        file_name = os.path.abspath(item) # get full path of files
        zip_ref = zipfile.ZipFile(file_name) # create zipfile object
        zip_ref.extractall(dir_name) # extract file to dir
        zip_ref.close() # close file
        print(f"Unzipped {file_name}")
        # os.remove(file_name)


print()
print("------------------")
print("Renaming Files")
print("------------------")
for dir, subdir, files in os.walk('/Users/natasha/Desktop/ntest'):
    for f in files:

        if key1 in f:
            print(f"Full Path Check: {os.path.join(dir)}")
            with open(os.path.join(dir, f), 'r') as infile:
                line = infile.read()
                if "Ucdavis3yrs" in line:
                    atlas = "_ucd3"
                    print(f"Found atlas {atlas}")
                elif "Pediatric4-8yrs" in line:
                    atlas = "_ped48"
                    print(f"Found atlas {atlas}")
                elif "Pediatric8-12yrs" in line:
                    atlas = "_ped812"
                    print(f"Found atlas {atlas}")
                elif "Adult" in line:
                    atlas = "_adult"
                    print(f"Found atlas {atlas}")
                else:
                    print("Nothing found, something is wrong")
                    # convert this if/elif block into a try/except block

        if key2 in f:
            p2 = os.path.join(dir, f)
            p3 = f.split(".")[0]
            print(f"ScanID: {p3}")
            k = p3 + atlas
            print(f"Folder will be renamed as: {k}")
            j = os.path.dirname(os.path.join(dir))
            print(f"Target folder to rename: {os.path.basename(j)}")
            os.rename(os.path.join(j), k)
            print(f"Renaming complete")
            print()

