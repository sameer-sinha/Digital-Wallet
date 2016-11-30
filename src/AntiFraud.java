package com.insight.pyamo;

public class AntiFraud {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Started");
		ReadDataCreateMap rdcm = new ReadDataCreateMap(
				"./paymo_input/batch_payment.txt ");
		Features feature = new Features("./paymo_input/stream_payment.txt ", rdcm);
		try {
			feature.firstFeature("./paymo_output/output1.txt ");
			feature.secondFeature("./paymo_output/output2.txt");
			feature.thirdFeature("./paymo_output/output3.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ended");
	}

}
