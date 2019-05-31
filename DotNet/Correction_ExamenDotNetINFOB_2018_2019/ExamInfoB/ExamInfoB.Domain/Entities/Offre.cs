using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamInfoB.Domain.Entities
{
    public class Offre
    {
        [DataType(DataType.Date)]
        public DateTime DatePublication { get; set; }
        public string Description { get; set; }
        public int IdOffre { get; set; }
        public string TitreOffre { get; set; }
        public string TypeContrat { get; set; }
        [Display(Name ="Entreprise")]
        public int? EntrepriseKey { get; set; }

        //Navigation Properties
        public virtual ICollection<Postulant> Postulants { get; set; }
        [ForeignKey("EntrepriseKey")]
        public virtual Entreprise Entreprise { get; set; }
    }
}
