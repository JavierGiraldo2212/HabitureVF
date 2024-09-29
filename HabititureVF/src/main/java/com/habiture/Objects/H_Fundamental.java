package com.habiture.Objects;

import com.habiture.Structures.NonLinear.*;
import com.habiture.Structures.Linear.*;

public class H_Fundamental {
	GenericAVL<Activity> TempActivities =  new GenericAVL<Activity>(Activity.byFecha());
	GenericAVL<Activity> ImpActivities =  new GenericAVL<Activity>(Activity.byImportancia());
	
	Stack<Activity> HistActivities = new Stack<Activity>();
	
	public void addActivity(Activity act) {
		TempActivities.insert(act);
		ImpActivities.insert(act);
	}
	
	public void removeActivity(Activity act) {
		TempActivities.remove(act);
		ImpActivities.remove(act);
	}
	
	public void addHistActivity(Activity act) {
		HistActivities.push(act);
	}
	
	public void removeHistActivity() {
		Activity act = HistActivities.pop();
		TempActivities.insert(act);
		ImpActivities.insert(act);
	}
	
	
}
