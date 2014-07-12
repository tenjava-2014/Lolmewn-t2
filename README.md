Lolmewn's ten.java submission
==============================

[![ten.java](https://cdn.mediacru.sh/hu4CJqRD7AiB.svg)](https://tenjava.com/)

This is a submission for the 2014 ten.java contest.

- __Theme:__ How can energy be harnessed and used in the minecraft world?
- __Time:__ Time 2 (7/12/2014 09:00 to 7/12/2014 19:00 UTC)
- __MC Version:__ 1.7.9 (latest Bukkit beta)
- __Stream URL:__ https://twitch.tv/Lolmewn

---------------------------------------

Compilation
-----------

- Download & Install [Maven 3](http://maven.apache.org/download.html)
- Clone the repository: `git clone https://github.com/tenjava/Lolmewn-t2`
- Compile and create the plugin package using Maven: `mvn`

Maven will download all required dependencies and build a ready-for-use plugin package!

---------------------------------------

Usage
-----

1. Install plugin
2. Do things with it

Description
-----------

This plugin implements magical powers from the realms of the End into Minecraft.
Exp drops are mana which can be used to cast spells.
Spells can be learnt through making a scroll (paper + nether star in crafting bench),
then going to a sign with [Spell Forge] on the first line, and learning a spell.
All spells require scrolls, some require extra items and XP levels.  You have a percentage
chance to learn this spell. If it goes wrong, you receive some damage.

When you've learnt a spell succesfully, you will receive the spell book. Rightclicking with the book
in your hand will activate the spell (at a mana-cost which is displayed in the lore of the book).