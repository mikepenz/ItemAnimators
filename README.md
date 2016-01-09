#ItemAnimators  [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.mikepenz/itemanimators/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/com.mikepenz/itemanimators) [![Join the chat at https://gitter.im/mikepenz/itemanimators](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/mikepenz/itemanimators?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

The **ItemAnimators** library comes with a huge collections of pre-created Animators for your RecyclerView.
It was created so developers can easly animate their RecyclerView. It also takes care about correctly handling all view states so you don't have to.

> **DISCLAIMER**: this library does not animate items on scroll, just when added, removed, moved, or changed

#Preview
##Screenshots

#Include in your project
##Using Maven
```javascript
compile('com.mikepenz:itemanimators:0.1.0-SNAPSHOT@aar') {
	transitive = true
}

//only on the SNAPSHOT repo right now:
repositories {
    maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
}
```

##How to use
```java
//just provide the animator to your RecyclerView
mRecyclerView.setItemAnimator(new ScaleUpAnimator());

//now it will automatically take care of all animations when you add, remove, chanage, move items
//It is very important that you use the correct notify methods if items were changed, otherwise the adapter can't animate the items
//Read more: http://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html#notifyDataSetChanged()
//If you want those things out of the box have a look at the **FastAdapter** it handles everything correctly for you
//https://github.com/mikepenz/FastAdapter
```

#Developed By

* Mike Penz
 * [mikepenz.com](http://mikepenz.com) - <mikepenz@gmail.com>
 * [paypal.me/mikepenz](http://paypal.me/mikepenz)

#License

    Copyright 2016 Mike Penz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
