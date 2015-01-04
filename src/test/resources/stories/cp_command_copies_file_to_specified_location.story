Story: cp command copies file to specified location

Meta:
@author Jacek Spolnik

Narrative:
    As a terminal user
    I want to copy file
        So that I can backup or reuse it

Scenario: users copy existing file
to backup file in the same directory
Given a terminal service with working directory set initially to /home/test
And a file /home/test/file_to_copy
When I run cp file_to_copy file_to_copy.bkp command
Then I am able to see that file_to_copy file exists
And I am able to see that file_to_copy.bkp file exists

Scenario: users copy existing file
to backup file in the new directory
passing relative path
Given a terminal service with working directory set initially to /home/test
And a file /home/test/file_to_copy
And a directory /home/test/new_dir
When I run cp file_to_copy new_dir/file_to_copy command
Then I am able to see that file_to_copy file exists
And I am able to see that new_dir/file_to_copy file exists