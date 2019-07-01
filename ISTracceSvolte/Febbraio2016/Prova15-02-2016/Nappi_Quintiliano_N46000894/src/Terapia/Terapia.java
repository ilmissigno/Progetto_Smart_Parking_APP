package Terapia;

import java.util.Date;

public class Terapia {

		private Integer IdTerapia;
		private String Descrizione;
		private Date DataInizio;
		private Date DataFine;
		
		public Terapia (){
			
		}
		
		public Terapia (Integer idt){
			super();
			this.setIdTerapia(idt);
			
		}
		
		public Terapia ( Date data1, Date data2){
			super();
			this.setDataInizio(data1);
			this.setDataFine(data2);
						
		}
		
		public Terapia(Integer id, String des, Date data1, Date data2){
			super();
			this.setIdTerapia(id);
			this.setDescrizione(des);
			this.setDataInizio(data1);
			this.setDataFine(data2);
		}

		public Integer getIdTerapia() {
			return IdTerapia;
		}

		public void setIdTerapia(Integer idTerapia) {
			IdTerapia = idTerapia;
		}

		public String getDescrizione() {
			return Descrizione;
		}

		public void setDescrizione(String descrizione) {
			Descrizione = descrizione;
		}

		public Date getDataInizio() {
			return DataInizio;
		}

		public void setDataInizio(Date dataInizio) {
			DataInizio = dataInizio;
		}

		public Date getDataFine() {
			return DataFine;
		}

		public void setDataFine(Date dataFine) {
			DataFine = dataFine;
		}
		
}
