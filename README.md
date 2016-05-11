#RecyclerViewDelegate

A Delegetion to handle RecycleView.Adapter<VH> multitype

##AndroidStudio
```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

```
dependencies {
	        compile 'com.github.kHRYSTAL:RecyclerViewAdapterDelegate:v1.1'
	}
```

##Eclipse
![image](https://github.com/kHRYSTAL/RecyclerViewAdapterDelegate/blob/master/screenshot/hehe.jpeg)

##Usage
###Step1
set module extends DelegateBaseModel and set it's delegationViewType

eg.

```java
public class Color extends DelegateBaseModel{}
```

```java
public class Red extends Color{
    public Red(){
        delegationViewType = 1;
    }
}
```

```java
public class Blue extends Color{
    public Blue(){
        delegationViewType = 2;
    }
}
```

###Step2
init DelegateAdapter and addDelegation

eg.

```
       DelegateAdapter<Color> adapter = new DelegateAdapter<>(this,datas);
        adapter.addDelegation(new Delegation<Color>(R.layout.red_item,1) {

            @Override
            protected void handle(final DelegateViewHolder holder, final Color data, final int position) {
			//TODO something
            }
        });

        adapter.addDelegation(new Delegation<Color>(R.layout.blue_item,2) {


            @Override
            protected void handle(final DelegateViewHolder holder, final Color data, final int position) {
			//TODO something
        });

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
    
```

DelegateViewHolder encapsulation some method optimize ```findViewById()```
if itemView has CustomView, you can use like ```(CustomView)holder.getView(id)```







##Thanks
*   [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates)
