# Project Report
# Group members: Danny Ullrich, Maxwell Boshaw, Caden Koscinski, Zachary Canali

Our motivation for this project was to create a modular text based adventure game, both from personal interest, and because we saw it as an opportunity to harness our collective skills. Throughout the whole process, we slowly began to divide our effort based on our personal skills. For each of us, this project can serve as a starting point for any future endeavors. If one of us were to decide to make another, serious, text adventure, we would simply use this code and change the scene files, while could be added or reduced based on the needs of the story. Since we have spent so much time working on this project, it proves to be a testament of our skill and dedication to the project, which is in itself a source of entertainment for whoever uses it. Users could also use this to create their own adventures, since the program is modular, not hardcoded. There is a small chance that we will continue to work on this text adventure in one way or another in the future, either for our own personal enjoyment, or in a future assignment, should it be allowed. We are all very proud of our finished product.

This project was a team effort as each member of our four person team had an important role. Danny and Caden worked together to write the majority of the code, with Danny writing most of the input class, while Caden workied on optimization and implementing the dictionary as well as the parsing system. Caden also had the idea for our 3D array method, which was used as the basis for the code. Zach wrote the beginning of the story with Max’s help, and Max finished the last chapter of the story with edits from the team. Zach also provided Caden and Danny with the functionality he needed to write the story the way he wanted. Max spent a good amount of time working on the UML diagrams and helping extensively with planning how the code will work in the initial stages. The code was a big challenge but the creation of the UML diagrams was equally difficult due to the complexity of the code as well as the fact that the code was being changed constantly. The story was also a challenge in its own right because everything needed to intertwine and connect properly. We also wanted to create a genuine story and world, which added a necessity for creativity in the project.

The class interaction of this program is highly complex for programmers of our level. With our three main classes being Input, Main, and Scene, we sometimes had trouble keeping track of what methods were in which class. As our program continued to evolve, we ended up moving methods and objects, condensing entire classes into one another, and changing methods entirely to meet our required needs. The basic interaction of our classes, even at surface level, is not very simplistic. The user puts input into the system, the main class interprets it and communicates with Input and/or Scene, Input and Scene interact with each other to find the proper scene files, the scene file gives an output, this output is then sent back through Input, Scene, and Main, which in turn gives the user his or her desired output. Overall, our code could be simplified and possibly streamlined to look nicer and run more efficiently, but for the purposes of the project we are satisfied with the current outcome.
| Input | Main | Scene |
| --- | --- | --- |
| - dict : String                   | + userInput : String | - sceneId : String |
| - keyIndex : int                  | + inputOk : boolean  | - sceneText : String |
| - keyNum : int                    | - | - keyNum : int |
|  - startNum : int                 | - | - keyIndex : int |
|  - vars : String                  | - | - |
| ---                               | --- | --- |
| << constructor >> input()         | + clearScreen() | << constructor >> Scene() |
| + getInput() : String             | - | + checkObject(String commandIn, String ObjectIn) : boolean |
| + initDictionary()                | - | + printScene() |
| + parse(String input) : String    | - | + readScene(String inputSceneId) |
|  + readConfig()                   | - | - |
|  + getVar(String varInput) : int  | - | - |
