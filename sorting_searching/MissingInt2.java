/**
* What if we have only 10 MB memory
*/
public class MissingInt2{	
	public static final int range= 1 << 20; // 2^ 20 bits ( 2^ 17 bits)
	public static fine int blockSize= Integer.MAX_VALUE/range+1;
	public static int findFirstMissingOne(File filename) throws IOException{
		int[] blocks= getCountPerBlock(filename);
		int index= findBlockWithLessCount(blocks);
		if(index==-1) return index;
		byte[] bitVector= getBitVector(index);
		int offset=findFirstZero(bitVector);
		if(offset==-1) return offset;
		return index* range+ offset;
	}
	
	public static int[] getCountPerBlock(File filename) throws IOException{
		int[] blocks= new int[blockSize];
		FileReader fileReader=new FileReader(file);
		Scanner scanner=new Scanner(fileReader);
		while(scanner.hasNextInt()){
			int n= scanner.nextInt();
			blocks[n/blockSize]++;
		}
		
		scanner.close();
		reader.close();
		return blocks;
	}

	public static int findBlockWithLessCount(int[] blocks){
		for(int i=0;i< blockSize;i++){
			if(blocks[i] < range) return i;
		}
		return -1;
	}

	public static byte[] getBitVector(int blockIndex){
		byte[] bitVector= new byte[range/8];
		int start= blockIndex*range;
		int end= start+range;
		FileReader fileReader=new FileReader(file);
		Scanner scanner=new Scanner(fileReader);
		while(scanner.hasNextInt()){
			int value=scanner.nextInt();
			if(value>= start && value< end){
				int offset= value - start;
				bitVector[offset/8] |= (1 << (offset%8));
			}
		}
		scanner.close();
		reader.close();
		return bitVector;
	}
	
	public int findFirstZero(byte[] bitVector){
	
		for(int i=0;i< bitVector.length;i++){
			for(int j=0;j<8;j++){
				if( (bitVector[i] & ( 1 << j)) ==0){
					return i*8+j;
				}
			}
		}
		return -1;
	}

}