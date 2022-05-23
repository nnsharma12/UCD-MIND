"""Assignment 9 - File Import"""

import sys
import math


# Data Functions =================================================================================
def convert_units(celsius_value, units):
    """Convert temperature from user input value to Fahrenheit, Kelvin"""
    # Temp in Celsius
    if units == 0:
        temp = celsius_value
    # Temp in Fahrenheit
    elif units == 1:
        temp = (1.8 * celsius_value) + (32)
    # Temp in Kelvin
    elif units == 2:
        temp = celsius_value + (273.15)
    else:
        temp = celsius_value

    return temp


def print_header():
    """Print a header/greeting."""
    print("STEM Center Temperature Project")
    print("Natasha Sharma")


def print_menu():
    """Display a menu to the user"""
    print("Main Menu \n"
          "--------- \n"
          "1 - Process a new data file \n"
          "2 - Choose units \n"
          "3 - Edit room filter \n"
          "4 - Show summary statistics \n"
          "5 - Show temperature by date and time \n"
          "6 - Show histogram of temperatures \n"
          "7 - Quit \n")


# stub functions
def new_file(dataset):
    print("New file function called")
    # ask user for a filename
    user_input = input("Please enter a filename: ")
    print(f"You entered: {user_input}")

    # use process_file() to load the data
    # if process_file() succeeds, report number of samples that were loaded using get_loaded_temps()
    if dataset.process_file(user_input) == True:
        count = dataset.get_loaded_temps()
        print(f"Number of loaded datasets: {count}")

        while True:
            user_name = str(input("Please enter a new name for this dataset: "))
            try:
                dataset.name = user_name
                print("Success")
                print(f"new name: {dataset.name}")
                break
            except:
                print("Please enter a name between 3-20 characters long")

        # return True


    else:
        # if process_file() fails to load data, complain
        # fall back to main
        print("Unable to load file, exiting...")
        return False




def choose_units():
    print("Choose units function called")


def print_summary_statistics(dataset, active_sensors):
    print("Print summary function called")


def print_temp_by_day_time(dataset, active_sensors):
    print("Print temp by day/time function called")


def print_histogram(dataset, active_sensors):
    print("Print histogram function called")



def recursive_sort(list_to_sort, key):
    """takes a list of items and sorts it depending on key"""
    list_to_sort = [i for i in list_to_sort]  # create a copy of the orig. list using list comp.
    if key == 0:  # if all passes are done, return list
        if len(list_to_sort) == 0:
            return []

        for i in range(len(list_to_sort) - 1):  # for the items in list
            if list_to_sort[i][0] > list_to_sort[i + 1][0]:  # if item is > than one after it, invert order
                list_to_sort[i + 1], list_to_sort[i] = list_to_sort[i], list_to_sort[i + 1]

        return recursive_sort(list_to_sort[:-1], 0) + [
            list_to_sort[len(list_to_sort) - 1]]  # repeat until no more items to sort


    elif key == 1:
        list_to_sort = [i for i in (list_to_sort)]
        if len(list_to_sort) == 0:
            return []

        for i in range(len(list_to_sort) - 1):
            if list_to_sort[i][1] > list_to_sort[i + 1][1]:
                list_to_sort[i + 1], list_to_sort[i] = list_to_sort[i], list_to_sort[i + 1]
        return recursive_sort(list_to_sort[:-1], 1) + [list_to_sort[len(list_to_sort) - 1]]



def print_filter(sensor_list, active_sensors):
    """print sorted list based on active sensors"""
    for sensor in sensor_list:  # iterate on all sensors(1-5), for each sensor, print the 1st and 2nd index
        print(f" {sensor[0]}: {sensor[1]}", end=' ')
        if int(sensor[2]) in active_sensors:
            print("[ACTIVE]")
        else:
            print()
    print()


def change_filter(sensor_list, active_sensors):
    """toggle state of sensors"""

    sensors_dict= {}
    for sensor in sensor_list:
        sensors_dict[sensor[0]] = sensor[2]

    while True:
        print()
        print_filter(sensor_list, active_sensors)


        #try:
        user_input = input("Type the sensor number to toggle (e.g.4201) or x to end: ")

        if user_input == "x":
            print("exiting")
            break

        if user_input in sensors_dict.keys():
            if int(sensors_dict[user_input]) not in active_sensors:
                active_sensors.append(int(sensors_dict[user_input]))
            elif int(sensors_dict[user_input]) in active_sensors:
                active_sensors.remove(int(sensors_dict[user_input]))

        else:
            print("Invalid sensor")






# Class  =================================================================================
class TempDataset:
    """ A class TempDataset to store temperature data

    Attributes
    data_set : a dictionary/list?
        data to be worked on
    name : a str
        name of the dataset to be worked on

    """

    # class attribute that will change over time
    objects_created = 0
    #data_set = []

    @classmethod
    def get_num_objects(cls):
        return TempDataset.objects_created


    # these are instance variables
    # every time a new object is created from the class
    # that new object or new instance(of the class)
    # gets its own copy/set of these instance variables
    # That's why they are called instance variables...
    # ... A new set is created every time a new instance of Pet is created.
    # instance methods are defined inside the _init_() and prefixed by self.

    def __init__(self):
        self._data_set = None
        self._name = "Unnamed"

        # each time an object is created, we update the count
        TempDataset.objects_created += 1


    @property
    def name(self):
        return self._name


    @name.setter
    def name(self, data_name):
        if len(data_name) >= 3 and len(data_name) <= 20:
            self._name = data_name
        elif len(data_name) < 3 or len(data_name) > 20:
            # raise ValueError("Please enter a name between 3-20 characters long")
            raise ValueError


    def process_file(self, filename):
        self._data_set = []
        try:
            my_file = open(filename, 'r')
            #self._data_set = []

            for next_line in my_file:
                current_line = next_line.split(",")
                if current_line[3] == "TEMP":
                    current_line_day = int(current_line[0])
                    current_line_timeofday = math.floor((float(current_line[1])) * 24 )
                    current_line_sensor = int(current_line[2])
                    current_line_temp = float(current_line[4])

                    list_tuple = (current_line_day, current_line_timeofday, current_line_sensor, current_line_temp)
                    #print(f"day: {list_tuple[0]}, time {list_tuple[1]}, sensor: {list_tuple[2]}, temp: {list_tuple[3]}")

                    # if we reinitialize _data_set inside the function, then use the following
                    self._data_set.append(list_tuple)
                    #print(self._data_set)

                    # if we reinitialize _data_set as class variable then use the following commands
                    #TempDataset.data_set.append(list_tuple)
                    #print(TempDataset.data_set)

            my_file.close()
            return True
        except FileNotFoundError:
            #print("File not loaded, quitting...")
            return False




    def get_summary_statistics(self, active_sensors):
        # return none if internal dataset is None
        # otherwise return a default tuple (0,0,0)
        if self._data_set is not None:
            return (0,0,0)


    def get_avg_temperature_day_time(self, active_sensors, day, time):
        # return None if the internal dataset is None
        # otherwise return 0
        if self._data_set is not None:
            return 0


    def get_num_temps(self, active_sensors, lower_bound, upper_bound):
        # return None if the internal dataset is None
        # otherwise return 0
        if self._data_set is not None:
            return 0


    def get_loaded_temps(self):
        # return None if we have not successfully loaded a data file
        # otherwise return the number of samples that were loaded (an int)
        if self._data_set is None:
            return None
        else:
            count = len(self._data_set)
            return count







# Main() =================================================================================
def main():
    current_set = TempDataset()
    """
    test_a = current_set.process_file("test_file.csv")
    print(test_a)
    test_b = current_set.get_loaded_temps()
    print(f"number of loaded datasets: {test_b}")
    """


    sensor_0 = ("4213", "STEM Center", "0")
    sensor_1 = ("4201", "Foundations Lab", "1")
    sensor_2 = ("4204", "CS Lab", "2")
    sensor_3 = ("4218", "Workshop Room", "3")
    sensor_4 = ("4205", "Tiled Room", "4")
    sensor_5 = ("Out", "Outside", "5")

    sensor_list = [sensor_0, sensor_1, sensor_2, sensor_3, sensor_4, sensor_5]
    active_sensors = [i for i in range(len(sensor_list))]
    sensor_list = recursive_sort(sensor_list, 0)

    print_header()


    while True:
        try:
            print("\n")
            print_menu()
            user_input = int(input("What is your choice: "))
        except ValueError:
            print("*** Please enter a number only ***")
        else:
            if user_input == 1:
                new_file(current_set)

            elif user_input == 2:
                choose_units()

            elif user_input == 3:
                change_filter(sensor_list, active_sensors)

            elif user_input == 4:
                print_summary_statistics(current_set, current_set)

            elif user_input == 5:
                print_temp_by_day_time(current_set, current_set)

            elif user_input == 6:
                print_histogram(current_set, current_set)

            elif user_input == 7:
                print("Quitting...\n"
                      "Thank you for using the STEM Center Temperature Project")
                if current_set._data_set is not None:  # DELETE BEFORE SUBMISSION
                    print([current_set._data_set[k] for k in range(0, 5000, 1000)])  # DELETE BEFORE SUBMISSION
                break
            else:
                print("Invalid Choice")



if __name__ == "__main__":
    main()