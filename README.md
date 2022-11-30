# scrsorter

### Vision

&emsp;When comes time of backup, many usually just dump photos from phone to 'puter. Some (like me myself) manually run some ugly shell scripts for basic sorting, p.e. subdirectories for each month. But when one need to find, for example, all screenshots of certain Irtegov's lecture, he needs to sift through all photos.  

On the other hand, we can *each day/week/etc* ask user *via notification* to markup a batch of screenshots with tags of some **categories**,
and then embed buttons for scripts doing some basic actions like:
* backup all new marked shots to specified location on pc;
* delete all marked and backed shots;
* show all for certain category or date;
* mb perform some abit more complicated queries. 

internal actions needed:
* easily manage categories (CRUD tags & manage dangling screenshots without category);
* determine or set manually location and format of screenshots of device;
* add remote for backups;
* set period for notifications;
* set max size of batch to markup (those left will be included in the next one)/ 

maybe, we can maintain connection with remote to load thumbnails & lists for given query & perform queries for whole database of screenshots

### Mockups:

[Mockplus](https://rp.mockplus.com/run/i-b9c73NMF/MnCD5_4_bd/eBZjKdLqm2)

### Simular apps:
    
Slidebox - unsupported since 2016, no path customization, no notification, no backups

### Project focus
* gaining basic mechanical skills in developing for Android;
* getting familiar with arch features and caveats of OS.

### Used libraries:
* [bumptech/glide](https://github.com/bumptech/glide) for images handling
* [tommybuonomo/dotsindicator](https://github.com/tommybuonomo/dotsindicator) for nicer markup screen
* [Baseflow/PhotoView](https://github.com/Baseflow/PhotoView) for lazy implementation of preview (it's not a focus of project anyways)
