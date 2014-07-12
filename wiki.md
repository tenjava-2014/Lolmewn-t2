Wiki for the Spells plugin
=========
Made by [@Lolmewn]

Config
------

```yaml
inventory:
    #The name of the Spell Forge inventory,
    #which opens when you click a [Spell Forge] sign
    name: "Spell forge"


spell:
    weather:
        #The duration of sunny weather, in seconds
        duration: 600
        
#Create your own spells!
custom:

    #The name of the spell
    NightTime:
    
        #A description which will be displayed in the item lore
        description:
        - "&eWhen the sun is no longer bright ..."
        - "&1... the monsters will rule the night"
        
        #Amount of mana it costs to cast this spell
        manacost: 1
        
        #Time after which the spell can be used again in seconds
        cooldown: 60
        
        #The chance in percent you have for learning the spell successfully
        learnchance: 40
        
        #Requirements for learning the spell. There are only three possible requirements right now
        forge-req:
        
        #amount of scrolls needed
        - SCROLL 5
        
        #Other itemstacks needed. Requires a [material] and an amount
        - ITEMSTACK ENDER_PEARL 3
        
        #levels needed
        - EXP 2
        
        #What happens on activation of the spell
        #Possible actives are: commands, console and broadcast
        #All take a list of strings, either to be executed by the player, console or broadcasted to everyone
        activate:
        
            #commands to run
            commands:
            - time set 12000
```

How to create scrolls
---------------------
![crafting recipe](https://dl.dropboxusercontent.com/u/7365249/ten.java/2014-07-12_19.48.14.png)

When you have scrolls, head over to a sign looking like this (or create one):
![Spell Forge](https://dl.dropboxusercontent.com/u/7365249/ten.java/2014-07-12_19.49.11.png)

This will open the Spell Forge, with some spells:
![Fireball](https://dl.dropboxusercontent.com/u/7365249/ten.java/2014-07-12_19.49.59.png)

And when you learnt a spell:
![Weather](https://dl.dropboxusercontent.com/u/7365249/ten.java/2014-07-12_19.59.31.png)

If you've lost your spell book, you can go back and get one again for free. Other users cannot cast spells if they haven't learned it yet.


API
---

To use the API, you first have to hook into it. To do this, use the following code:
```java
private SpellsAPI spellsAPI;

private boolean setupSpellsAPI(){
    RegisteredServiceProvider<SpellsAPI> spells = getServer().getServicesManager().getRegistration(nl.lolmewn.tenjava.api.SpellsAPI.class);
    if (spells!= null) {
        spellsAPI = spells.getProvider();
    }
    return (spellsAPI != null);
}
```

The API has complete Javadocs coverage, and it should be super easy for you to add new Spells. 

[@Lolmewn]:http://twitter.com/Lolmewn
[material]:http://jd.bukkit.org/rb/apidocs/org/bukkit/Material.html