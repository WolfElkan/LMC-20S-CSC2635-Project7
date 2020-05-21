# Summary Questions

## Describe how you approached and solved the problem(s). 



## Where did you have trouble? How did you move forward? What topics still confuse you? 

One of the most challenging aspects was working with Java's static type system.  For example, I created a `Column` object for each column in the CSV file which had a method `read()` to translate the string it read from the file to something that could be set as an instance variable in the `Email` object.  However, the file contained both strings and dates, so the `Column` object had to return separate types.  Furthermore, I was calling this method in a loop, so the same line of code had to yield `String` and `Date` objects.  

I ultimately decided that `Column.read()` would always return a `String`, and then the `Email` object would deal with converting that string to the proper internal type.

Python's dynamic typing has spoiled me.  

## What did you learn from this assignment? (be specific)

I learned that I need to improve my workflow.  I need to make a functioning prototype first and modularize and improve second.  Perhaps I should build a skeleton of the application, with placeholder methods if necessary, and then fill them in from there.  My mistake during this project was to write methods and even whole classes that I thought would be used in the future, without first writing the logic that would *enable* them to be used.  

One aspect in which I did do this well was in the `onOpen()` and `onClose()` methods.  I wrote the logic that called these methods in the `Project.main` method while the methods themselves were still blank.  In the future, I should do this with all modules.

## If you worked in pairs, how did divide up the work?

I completed this assignment alone.