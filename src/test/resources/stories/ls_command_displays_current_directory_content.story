Meta:
@author Jacek Spolnik

Narrative:
As a terminal user
I want to list directory content
So that I can find the interesting part

Scenario: ls command is run
for /home/test as working directory
Given a terminal service with working directory set to /home/test
And new three new directories created with names one two three
When I run ls command
Then it displays all newly created directories as one three two