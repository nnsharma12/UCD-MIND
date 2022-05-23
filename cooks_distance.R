library(ggplot2)
# read csv file 
data_file <- read.csv("exercise_data.csv", stringsAsFactors = FALSE)
data_file
# load data file to data.frame
df <- data.frame(data_file)
df
# set day 7 to extreme value
df$steps_counted[7] <- -70
df$steps_counted


# write linear model function
lin_mod<- function(dependent_var1, independent_var1, data_set) {
  test_model <- lm (dependent_var1 ~ independent_var1, data = data_set)
  return(test_model)
}




print("PERFORMING FUNCTION CHECK")
func_test <- lin_mod(df$steps_counted, df$day_number, df)
func_test


print("PERFORMING HARD CHECK")
manual_model <- lm(df$steps_counted ~ df$day_number, data = df)
manual_model

boxplot(df$steps_counted, xlab = "days", ylab = "steps taken", main = "exercise data outliers",
        col = "white", border = "purple", horizontal = FALSE)

