# Project 7 – Final Project

*CSC2635 Computer Science II*

## Objective

Be able to write a program in Java that implements the new topics of sorting, searching and binary file IO, as well as course-based topics such as object-oriented programming and graphical user interfaces. 

## Instructions

1.	You may, if you’d like, select a partner (makes the design/creative component more fun)
2.	Review topic based materials
3.	Select a project idea from the section on project options. 
4.	Review the rubric so you understand how your solution will be evaluated. 
5.	Implement your solution in Java using the IDE of your choice.  If your project is well-designed then implementation should be easy
6.	Test, test, test!!!
7.	Write a summary  
8.	Submit
9.	Demonstrate your running program to instructor. Be prepared to show all possible test cases and answer project-related questions.

## New Material (Readings, Videos, etc&hellip;)

As this is a final project, you will be expected to implement concepts that we’ve covered throughout the semester (GUI and Object-Oriented Programming), in addition to the following new concepts: 
* Reading 
    * Horstmann Chapter 14: Sorting and Searching
    * Horstmann Chapter 19: Streams (specifically, Object streams in section 19.4)
* Lectures
    * Searching and Sorting lectures 
    * Streams lecture, specially object streams
 
## Project Options
### 1. Mailbox
#### Description
	
Write a GUI-based Mailbox tool that allows a user to

- Create emails  (you don’t need to actually send an email)
- SORT list of emails using the selection sort strategy.
- SEARCH for a specific email in a variety of ways (i.e., by date, by sender, etc.).  

In terms of object-oriented design, you should have a Mailbox class that holds a list of objects of type Email.  

The Email class should have the following instance variables: date sent, sender, priority, subject line, read/unread, receiver and content.   You may discover that you need other instance variables as you proceed.  

Both the Mailbox class and the Email class should be Serializable. 

The Mailbox object (instance) is automatically written out to an external FILE before the program stops.  Then the Mailbox is automatically read in from the external FILE when the program starts. When you first run your Mailbox program the file would not be found; you program should not fail, instead it should just display an empty list of emails. 

Please review and follow good object-oriented design practices. 
	
#### Notes
* The GUI Design  is up to you ( i.e., how you display the emails list, how you request sorting/searching fields, etc&hellip;). 
* File IO should be handled using object streams and serialization (easiest way to read in objects).
* You have creative freedom to add any additional functionality that you like. 

### 2. Sorting

#### Description

Write your own GUI program that demonstrates the behavior of the selection sort and merge sort algorithms. It should be written so that additional sorting methods could be easily added. 

#### Notes

Please see instructor if you’re interested. 

### Student Proposed Project

Especially recommended if student has learned this material before and needs more of a challenge.

#### Conditions
1.	Project was not implemented for a prior class (or for any other reason). 
2.	It is related to the current topic 
3.	It is of greater or equal complexity to the other options. 
4.	Student gets the instructors’ approval

[Return to Instructions](#instructions)

## Rubric

Type|Component|Points|Notes (if any)
---|---|---|---
Required|Compiles|20|
Required|Executes|20| 
Required|Correct|40|According to project instructions. (if it doesn’t compile and execute it’s not correct)
Required|Demo|15|To instructor&hellip;
Required|Summary|5	
EC|Depends on problem.|varies|These require you to do on your own and may not have been discussed in class. 
Penalty|Lateness|-20|&hellip;**per day** (limited time until the end of the semester). 

[Return to Instructions](#instructions)

## Summary

Answer the following questions either in paragraph form or as answers to the numbered items: 

1.	Describe how you approached and solved the problem(s). 
2.	Where did you have trouble? How did you move forward? What topics still confuse you? 
3.	What did you learn from this assignment? (be specific)
4.	If you worked in pairs, how did divide up the work?

Note: Each student must write their own, individual summary, even if you worked in pairs on the problems.

[Return to Instructions](#instructions)

## Submitting Your Work

1.	Make sure that your name(s) are in all of your project source files.  
2.	Place all source, test and summary files in a .zip compressed file. You do not need to submit the executable files
3.	In Canvas attach your file to the assignment and submit.

[Return to Instructions](#instructions)
