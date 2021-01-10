# Introduction-to-LightTable

A guide to setting up and using the features of LightTable.

Our git cloning url is to the right, or you can just click Clone in Desktop to get a copy of this repository on your personal computer.

## Archival Notice

This repository has been archived and designated as read-only. From GitHub's documentation:

> This will make the emmasax4/Introduction-to-LightTable repository, issues, pull requests, labels, milestones, projects, wiki, releases, commits, tags, branches, reactions and comments read-only and disable any future comments. The repository can still be forked.

To unarchive this project at any time, please reach out to me at https://emmasax4.com/contact-me/.

## Java setup

Firstly, you should have Java, and the Java Developers Kit, already installed on your computer. If it isn't installed already, go to http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html and click the JDK option for your operating system. Follow the given directions for completing this download.

## Leiningen setup

You can download Leiningen from http://leiningen.org/. Make sure you have a reliable internet connection since leiningen runs a self-install script. Start by downloading the lein script. If when trying to download the lein script, the script just opens (instead of downloading), first try right-clicking and saving it as lein to a place such as the desktop. If that doesn't work, then try making a new text file and copy-pasting the whole script into the new file on your computer and saving it as lein to a location such as the desktop. Then cd into the place you've saved the file, such as desktop. Then move it using the terminal by typing

```bash
sudo mv lein /usr/bin/lein
```

Then, make it executable by typing

```bash
chmod a+x usr/bin/lein
```

into the command line. After this, then type

```bash
lein
```

into the command line, and watch it self-install a bunch of things. Then, type lein once more, and when a list of leiningen commands appear, you know that leiningen is fully installed!

## LightTable setup

You can download LightTable from http://www.lighttable.com/. Downloading LightTable is pretty easy. Follow the steps on the LightTable download site, move the folder that appears to the location you want on your computer, and you're set to go!

## Autoexpect setup

For expectations, there is a little bit of formatting needed to make sure everything works correctly. You can make a new project by cd-ing into the desired directory and then typing:

``` bash
lein new your-project-name
```

into the command line. We've named our project: test-project. It should say that a new project has been created. Open LightTable and open the Workspace by clicking View -> Workspace. Right click on the workspace area and click Add Folder. Find where you've put your project and click Upload. Open the file project.clj by single-clicking on the name of your project and then single-clicking on project.clj. What's currently in the file should look something like this:

```bash
(defproject test-project "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.3.0"]])
```

Change your project.clj file by adding the expectations library and autoexpect plugin, so that it looks like this:

```bash
(defproject test-project "1.0.0-SNAPSHOT"
  :description "A project for showing basic uses of LightTable and Autoexpect"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [expectations "2.0.6"]]
  :plugins [[lein-autoexpect "1.0"]])
```

making sure that you add the expectations dependency and the lein-autoexpect plugin; the description can be whatever you like. Save the file. Next, open up your test file by clicking test/  and clicking through as many nested folders as needed to get to core_test.clj. Once open, core_test.clj should look something like this:

```bash
(ns test-project.core-test
  (:require [clojure.test :refer :all]
            [test-project.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))
```

Replace the phrase `clojure.test` to the word expectations. It should look something like this:

```bash
(ns test-project.core-test
  (:require [expectations :refer :all]
            [test-project.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))
```

Now, you can erase the call to deftest, and replace it with an expectations test, for instance:

```bash
(ns test-project.core-test
  (:require [expectations :refer :all]
            [test-project.core :refer :all]))

(expect 5 (+ 2 3))
```

This is the most basic form of testing using expectations. Now, be sure you save the file and then go to the command line, cd into the project directory, and type

```bash
lein autoexpect
```

and then hit enter. Some extra things might print; you can ignore those. At the end, it should say something like:

```bash
Ran 1 tests containing 1 assertions in 14 msecs
0 failures, 0 errors.
```

It might even use pretty colors!

Now you can happily code in LightTable and every time you save, your tests will run automatically and show the results in the terminal. Note, if you want autoexpect to stop running, hit Ctrl+c (or Ctrl+z on Mac).

Happy coding!

For more information on Autoexpect, take a look at this link: http://jayfields.com/expectations/installing.html

#Uninstalling the Programs Listed Above

If you ever would like to uninstall any of the software explained above, then feel free to follow these steps.

Java: If you would like to uninstall Java (although we have no idea why you would feel the need to), you can go to this site:

```bash
java.com/remove
```
and follow the directions to uninstall Java.

Leiningen: First, you must make sure that you delete both the lein script (the file that you have downloaded) and the .lein hidden folder that stores leiningen internal data. This would probably be somewhere like your home directory, but it could be other places. Since .lein is a hidden folder (the name starts with a dot), you need to use the option for showing hidden files on your system. On mac and linux on the command line use the command to list hidden files:

```bash
ls -a
```

On windows, use:

```bash
dir /a
```

After you have deleted by the lein script and the .lein fiolder, check that leiningen is fully uninstalled by typing

```bash
lein
```

into your command line. No leiningen commands should appear.

LightTable: On windows, you can use the uninstall tool, otherwise, your best bet would be to just drag it to trash and then empty your trash.

Autoexpect: Autoexpect isn't a thing we really "installed," so therefore, there is no uninstalling. If you would like to remove expectations or autoexpect plugin from from your project, remove references to them from your project.clj file.

Any comments and/or confusions? We'd love to hear from you! Create a [GitHub issue](https://github.com/emmasax4/Introduction-to-LightTable/issues/new) if you have any concerns!
