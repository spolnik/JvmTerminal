Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to create new empty file
So that I will be able to use it

Scenario: touch command is run
for /home/test working directory
with temp_file file name as argument
Given a terminal service with working directory set to /home/test
When I run touch temp_file command
Then I am able to see that /home/test/temp_file file is created

Given a terminal service with working directory set to /home/test
When I run touch /home/test/temp_file_2 command
Then I am able to see that /home/test/temp_file_2 file is created