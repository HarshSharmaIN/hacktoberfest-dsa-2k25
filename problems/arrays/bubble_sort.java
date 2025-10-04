import java.util.*;
class sorting{
  public static void main(sting args []){
    int arr[] = {7,8,3,1,2};
    for(int i=0; i<arr.lenth-1; i++){
       for(int j=0; i<arr.lenth-i-1; j++){
         if(arr[j]>arr[j+1]){
           int temp = arr[j];
           arr[j] = arr[j+1];
           arr[j+1] = temp;
         }
       }
    }
    for(int i=0; i<arr.lenth-1; i++){
      system.out.println(arr[i]);
    }
  }
}
