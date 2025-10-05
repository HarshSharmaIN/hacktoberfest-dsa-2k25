class Solution {
    public int celebrity(int mat[][]) {
        int n = mat.length;
        int i = 0;
        int j = n-1;
        
        while(i < j)
        {
            if(mat[i][j] == 1)
            {
                // i knows j, so i cannot be celebrity
                i++;
            }
            else
            {
                // i does not know j, so j cannot be calebrity
                j--;
            }
        }
        
        int candidate = i;
        
        for(int k = 0; k < n; k++)
        {
            if(candidate == k) continue;
            if(mat[candidate][k] == 1 || mat[k][candidate] == 0)
            {
                return -1;
            }
        }
        
        return candidate;
    }
}