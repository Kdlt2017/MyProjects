using ExamInfoB.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExamInfoB.Service.Services
{
    public interface IExamService
    {
        IEnumerable<Offre> ListOfferOfMonth();
        Dictionary<string,int> NbOfferPost();
        IEnumerable<Entreprise> FirstTwoEntreprise(string contrat);
        int NbEntreprisePME();
        IEnumerable<Entreprise> EntrepriseParSecteur(string ville);
        void AddOffre(Offre Offre);
        IEnumerable<Entreprise> ListEntreprise();
        IEnumerable<Offre> ListOffres();

    }
}
