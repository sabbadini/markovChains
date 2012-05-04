import Jama.Matrix;

public class Weather {

	public static void main(String[] args) {
		
		/*weather forecasting given
		 * state space,
		 * first-order (memoryless) Markov Process with transition matrix,
		 * initial state,
		 * forecast period
		 */
		//------------------------
		
		//setup
		//------------------------
		// define the state space:
		int states = 3;
		String[] space = new String[states];
		space[0] = "sunny";
		space[1] = "cloudy";
		space[2] = "rain";
		//define the transition matrix:
		double[][] trans = new double[states][states];
		trans[0][0] = 0.75;//P(0|0)
		trans[0][1] = 0.20;//P(1|0)
		trans[0][2] = 0.05;//P(2|0)
		trans[1][0] = 0.50;//P(0|1)
		trans[1][1] = 0.25;//P(1|1)
		trans[1][2] = 0.25;//P(2|1)
		trans[2][0] = 0.25;//P(0|2)
		trans[2][1] = 0.50;//P(1|2)
		trans[2][2] = 0.25;//P(2|2)
		//choose the initial state:
		int state = 2;
		//choose the future time period to forecast:
		int period = 15;
		
		//forecast
		//------------------------
		double[][] initState = new double[1][states];
		for(int i=0;i<states;i++){
			if(i==state){
				initState[0][i]=1;
			}
			else{
				initState[0][i]=0;
			}
		}
		Matrix Trans = new Matrix(trans);
		Matrix FutureTrans = new Matrix(trans);
		for(int i=1;i<period;i++){
			FutureTrans = FutureTrans.times(Trans);
		}
		Matrix InitState = new Matrix(initState);
		Matrix OutputState = InitState.times(FutureTrans);
		
		//print
		//------------------------
		System.out.print("States:");
		for(int i=0;i<states;i++){
			System.out.print(" "+space[i]);
		}
		System.out.println("\nTransition matrix:");
		Trans.print(3,3);
		System.out.println("Initial State: "+space[state]);
		System.out.println("Forecast period: "+period);
		System.out.println("Forecast:");
		for(int i=0;i<states;i++){
			System.out.print(space[i]+" ");
		}
		OutputState.print(3,3);

	}

}
