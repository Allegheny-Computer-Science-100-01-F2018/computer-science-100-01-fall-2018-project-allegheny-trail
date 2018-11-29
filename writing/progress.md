# Progress report
# Group members: Danny Ullrich, Maxwell Boshaw, Caden Koscinski, Zachary Canali

| Input | Main | Scene |
| --- | --- | --- |
| - dict : String                   | + userInput : String | - sceneId : String |
| - keyIndex : int                  | - inputOk : boolean  | - sceneText : String |
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
