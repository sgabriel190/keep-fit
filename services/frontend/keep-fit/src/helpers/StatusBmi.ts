const statusBmi = (bmi: number) => {
    if(bmi === 0){
        return "";
    }
    if(bmi < 18.5){
        return "Underweight";
    }
    if(bmi < 25){
        return "Normal";
    }
    if(bmi < 30){
        return "Overweight";
    }
    if(bmi < 35){
        return "Obese Class I";
    }
    if(bmi < 40){
        return "Obese Class II";
    }
    return "Obese Class III"
}

export default statusBmi;
