Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to list directory content
So that I can find the interesting part

Scenario: ls command is run
for /home/test as working directory
Given a terminal service with working directory set to /home/test
And new directories created with names one two three
When I run ls command
Then it displays output as one three two

Given a terminal service with working directory set to /home/test
And new directories created with names the_only_dir
When I run ls command
Then it displays output as the_only_dir

Given a terminal service with working directory set to /home/test
When I run ls /home command
Then it displays output as test