Story: move command moves file to specified location

Meta:
@author Jacek Spolnik

Narrative:
    As a terminal user
    I want to move/rename file
        So that I can use better name

Scenario: user moves existing file
to new file name in the same directory
Given a terminal service with working directory set initially to /home/test
And a file /home/test/file_to_move
When I run mv file_to_move renamed_file command
Then I am able to see that file_to_move file does not exist
And I am able to see that renamed_file file exists

Scenario: user moves existing file
to new file name in the new directory
passing relative path
Given a terminal service with working directory set initially to /home/test
And a file /home/test/file_to_move
And a directory /home/test/new_dir
When I run mv file_to_move new_dir/renamed_file command
Then I am able to see that file_to_move file does not exist
And I am able to see that new_dir/renamed_file file exists