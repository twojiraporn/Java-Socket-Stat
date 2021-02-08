public class Calculator {


    public double avg(String[] arrOfInput){
        double sum = 0;
        double avg ;

        for (String i : arrOfInput) {
            System.out.println(i);
            sum += Double.parseDouble(i);
        }

        avg = sum/arrOfInput.length;
        return avg;
    }

    public double mean(String[] arrOfInput) {

        double mean = 0;
        double tmp = 0;
        int len = arrOfInput.length;
        double[] srt = new double[len];

        for (int i = 0; i < len; i++) {
            srt[i] = Double.parseDouble(arrOfInput[i]);
        }
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < (len - i); j++) {
                if (srt[j - 1] > srt[j]) {
                    tmp = srt[j - 1];
                    srt[j - 1] = srt[j];
                    srt[j] = tmp;
                }
            }
        }
        if (len % 2 != 0) {
            mean = srt[(len / 2)];
        } else if (len % 2 == 0) {
            double ans;
            ans = srt[(len / 2) - 1] + srt[len / 2];
            mean = ans / 2;
        }
        return mean;
    }

    public double mode(String[] arrOfInput){
        double maxValue = 0, maxCount = 0;
        int len = arrOfInput.length;

        double[] srt = new double[len];

        for (int i = 0; i < len; i++) {
            srt[i] = Double.parseDouble(arrOfInput[i]);
        }

        for (int i = 0; i < len; ++i)
        {
            int count = 0;
            for (int j = 0; j < len ; ++j)
            {
                if (srt[j] == srt[i])
                    ++count;
            }
            if (count > maxCount)
            {
                maxCount = count;
                maxValue = srt[i];
            }
        }
        return  maxValue;
    }

    public double standardDeviation(String[] arrOfInput){
        double sum = 0;
        double avg ;
        double sd = 0 ;

        int len = arrOfInput.length;

        double[] srt = new double[len];

        for (int i = 0; i < len; i++) {
            srt[i] = Double.parseDouble(arrOfInput[i]);
            sum += srt[i];
        }

        avg = sum/arrOfInput.length;
        for(double num : srt) {
            sd += Math.pow(num - avg, 2);
        }
        return Math.sqrt(sd/len) ;
    }

    public double deviation(String[] arrOfInput){
        double sum = 0;
        double avg ;
        double sd = 0 ;

        int len = arrOfInput.length;

        double[] srt = new double[len];

        for (int i = 0; i < len; i++) {
            srt[i] = Double.parseDouble(arrOfInput[i]);
            sum += srt[i];
        }

        avg = sum/arrOfInput.length;
        for(double num : srt) {
            sd += Math.pow(num - avg, 2);
        }
        return sd/len ;
    }

}
