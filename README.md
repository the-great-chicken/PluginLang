
# the-great-chicken / PluginLang

PluginLang is a Minecraft Plugin that creates a langage interpreted by Java to write a plugin code without knowing anything about java and without having to install any dependencies such as Maven. The goal is to make easier some features such as GUIs, and easy actions to allow for Just-In Time reloads on the server without having to rebuild the plugin and to allow more advanced actions with the procedures.

# Keywords

## $ref

A variable can be accessed by adding a $ sign in front of the name. It is called a reference in the langage.

## %use 

%use is one of the first keywords, it allows to push an action on a player interaction or on an object that allows to be pushed on.

For example, if you have a refence to an action $action, you can add this action to the PLAYER_LOGIN_EVENT by doing

```
%use PLAYER_LOGIN_EVENT $action
```

## %define

%define allows you to define a function-like object. It allows you to create dynamic and static functions. After the %define keyword, it is required for you to add a secondary keyword that shows what kind of object you would like to create with the define. After the object type, you will be able to specify the name and a few parameters inside a parenthesis. 

Parameters will only be accepted for the %procedure secondary keyword or extensions of %procedure. The inner body of the object will be the following lines that start with a pipe and that will allow you to define the object data further or the function itself.

### %procedure

%procedure is a function like objects and implements the %setvar, %expr and %return parameters, an example of this can be :

```
%define %procedure norm2_xyz (PlayerA, PlayerB)
| %setvar dx %expr($PlayerA.x - $PlayerB.x)
| %setvar dy %expr($PlayerA.y - $PlayerB.y)
| %setvar dz %expr($PlayerA.z - $PlayerB.z)
| %return %expr(dx * dx + dy * dy + dz * dz)
```

### %action

%action is an object representing an action that the player can take. It implements the parameters %type, %value.

If %type is COMMAND, then %value will represent the command the player will execute. 
If %type is PROCEDURE, then it will launch the procedure stored in %value and give it as parameter if needed the player that launched the action.

Other type of actions will be extended with external plugins. An example of thing you could execute.

### %command

%command is an object that creates a minecraft command. You can give it a procedure or an action associated with it with the %target keyword. You can give it a name with the %name keyword and a description with the %description keyword.

