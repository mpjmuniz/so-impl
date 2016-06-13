package so;

import java.util.ArrayList;
import java.util.List;

public class Swapper{
	/*
	 *	1. Find the location of the desired page on the disk.
	 *	2. Find a free frame:
	 *	a. If there is a free frame, use it.
	 *	b. If there is no free frame, use a page-replacement algorithm to select
	 *	a victim frame.
	 *	c. Write the victim frame to the disk; change the page and frame tables
	 *	accordingly.
	 *	3. Read the desired page into the newly freed frame; change the page and
	 *	frame tables.
	 *	4. Continue the user process from where the page fault occurred.
	 */
	
	private List<Integer> livres = new ArrayList<>(1024);
	
	public Swapper(){
		
	}
	
	/*
	 * 	swap-in-processo: Trazer da memória processo inteiro
	 * */
	public void swap_in_processo(int pid){
		
	}
	
	/*
	 * 	swap-out-processo: Guardar na memória processo inteiro
	 * */
	public void swap_out_processo(int pid){
		
	}
	
	/*
	 *	Swap-in: Traz página da memória secundária para a memória principal
	 * */
	public void swap_in(int end_log){
		
	}
	
	/*
	 * 	Swap-out: Guarda página na memória
	 * */
	public void swap_out(){
		
	}

}
