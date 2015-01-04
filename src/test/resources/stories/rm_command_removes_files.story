Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to remove files
So that I can delete files I don't need any more

Scenario: rm temp_file command is run
for /home/test working directory
to remove single file
Given a terminal service with working directory set to /home/test
And newly created file /home/test/temp_file
When I run rm temp_file command
Then file /home/test/temp_file is removed

Scenario: rm temp_file_1 temp_file_2 command is run
for /home/test working directory
to remove many files
Given a terminal service with working directory set to /home/test
And newly created file /home/test/temp_file_1
And newly created file /home/test/temp_file_2
When I run rm temp_file_1 temp_file_2 command
Then file /home/test/temp_file_1 is removed
And file /home/test/temp_file_2 is removed