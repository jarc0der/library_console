# ![alt text](http://botscrew.com/img/logo.svg) Library Console Application
```
Sorry for using BotsCrew's website in packages name and logo if it isn't allowed :)
```

This application allows to create and maintain your own library.

## Installing

Just clone project from repository and import into your IDE.

## Starting
Make sure that Maven has loaded all dependencies and has built a project

Run `main` method in the class:
```
com.botscrew.library.App
```

## Build with
```
Maven
```
## Usage

### Author 
```
Add new author: author 'first_name' 'last_name'
```
Just add a new author to the DB

### Book
```
Add new book: add
```
Command `add` includes subcommand `author param1 param2` for adding new author for the book
After type `add` program will ask
```
Do you want to choose author? y/n
```
y(yes) - means to choose an author from the list
n(no or another symbol) - means to create a new author and then add a new book for him

```
Edit book: edit 'book_name'
```
You can edit a name of an existing book by entering a name of the book. Then you should enter a new name.

```
remove 'book_name'
```
This command removes existing book from DB

```
list
```
This command shows all books with authors sorted by book name.

## Technologies
### RDBMS
```
Hyper SQL(in memory)
```
### Frameworks and libs.
```
ORM Hibernate
JUnit
```


