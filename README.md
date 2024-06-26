
# Souls Awakening
Minecraft mod based on the anime/manga of the author Tite Kubo called "Bleach".

## Explanation
For overall explanation of the gameplay working of the mod please refer to the [gameplay](./GAMEPLAY.md) explanation.



## Community

Join the community:
https://discord.gg/wpKYdRqsaD
## Documentation


Besides the basic comments in the code there is no documentation at the moment.

## Authors

- [@Beosti](https://github.com/Ziroxis)

## Contributors
- [@Kaiyo]()

## Honorable Mentions
- [@Wynd](https://github.com/Wynd)

## Contributing
You can always contribute by making a pull request like so:
##### 1. Fork it (git clone https://github.com/Beosti/souls-awakening.git)
##### 2. Create your branch (git checkout -b changes)
##### 3. Commit your changes (git commit -m "changed something")
##### 4. Push to branch (git push origin my-changes)
##### 5. Create new pull request
## Rules for contribution


### Code style
#### if statements
Do not make a condition and the code inside that body on the same line such as this:

    if (condition) {return;}
but do this:
    
    if (condition)
        return;
#### no nesting
Try to avoid nesting as much as possible, example:

    void example()
    {
        if (statement)
        {
            // more code here
        }
    }
preferably do this:

    void example()
    {
        if (!statement)
            return;
        // more code here
    }
#### Document your code
If you're adding or changing something and it's getting big, always add documentation even if it is short. Of course do not overdo it. Don't do:

    ReallyBigClass{
        void doesSomething()
        {
            // does something
        }
        
        void doesAnotherThing()
        {
            // does another thing
        }
    }
But do this:

    /**
     * This class does something and another thing
     * Something happens this way here {@link #doesSomething()} 
     * Another thing happens this way here {@link #doesAnotherThing()}
    */
    ReallyBigClass{
        void doesSomething()
        {
            // does something
        }
        
        void doesAnotherThing()
        {
            // does another thing
        }
    }

More short form comments can always be handy but are not needed if explained well at the head of the class.
#### Bugfixes
Feel free to bugfix at your hearts content and make a pull request. Preferably with the code being streamlined and what bug you fixed.
#### Additions
Feel free to make additions but be sure you have properly read the code style part of the text.
#### Changes in api
If you want to change something in the [API](src/main/java/com/yuanno/soulsawakening/ability/api), because you are not able to make something you want with what there is now. Preferably add unto the interface you can use and if not add your own interface and if needed be your own logic. 
