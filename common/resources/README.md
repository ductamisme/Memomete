Resource naming convention
Strings
String names start with a prefix that identifies the section they belong to. We use <HOW>_<DESCRIPTION> for strings naming. <HOW> to indicate reason of the string will be used & description give any extra information.

For ex:

<string name="label_home">Home</string> : how = label & home is description of the string.
<string name="hint_user_name">Enter Your Name</string> : how = hint & user_name is the description of string.

Drawables
We use <WHAT>_<DESCRIPTION> for strings naming. <WHAT> is Button,Dialog,Divider,Icon, etc & description give any extra information.
https://miro.medium.com/v2/resize:fit:720/format:webp/1*YrWmzRSvz9BWosoE5Qtykg.png

Dimensions
Apps should only define a limited set of dimensions, which are constantly reused.

<WHAT>_<WHERE>_<SIZE> :

<WHAT> : It can be width(in dp), height (in dp), size (if width == height), margin(in dp), padding(in dp), elevation(in dp). text_size(in sp).
<WHERE>: It can be any specific item or remove this section if used throughout the application.
<SIZE>: It can be dp or sp.

https://miro.medium.com/v2/resize:fit:640/format:webp/1*66JEKhu7WqtPMkjegXMmgQ.png