package com.habiture.Objects;

import com.habiture.Structures.NonLinear.*;
import com.habiture.Structures.Linear.*;

public class H_Fundamental {
	public GenericAVL<Activity> TempActivities = new GenericAVL<Activity>(Activity.byFecha());
	public GenericAVL<Activity> ImpActivities = new GenericAVL<Activity>(Activity.byImportancia());

	public Stack<Activity> HistActivities = new Stack<Activity>();
	public Stack<Activity> DoneActivities = new Stack<Activity>();

	public void addActivity(Activity act) {
		TempActivities.insert(act);
		ImpActivities.insert(act);
	}

	// delete - send to history
	public void removeActivity(Activity act) {
		TempActivities.remove(act);
		ImpActivities.remove(act);
		
		HistActivities.push(act);
	}

	// delete - send to done
	public void doneActivity(Activity act) {
		TempActivities.remove(act);
		ImpActivities.remove(act);
		
		DoneActivities .push(act);
	}
	
	// send to AVL from deleted history
	public void removeHistActivity() {
		Activity act = HistActivities.pop();
		TempActivities.insert(act);
		ImpActivities.insert(act);
	}
	
	// send to AVL from done
	public void removeDoneActivity() {
		Activity act = DoneActivities.pop();
		TempActivities.insert(act);
		ImpActivities.insert(act);
	}
	
}
