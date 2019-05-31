using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ExamInfoB.Domain.Entities;
using ExamInfoB.Data;

namespace ExamInfoB.Service.Services
{
    public class ExamenService : IExamService
    {
        ExamInfoBContext ctx;
        public ExamenService()
        {
            ctx = new ExamInfoBContext();
        }

        public void AddOffre(Offre Offre)
        {
            ctx.Offres.Add(Offre);
            ctx.SaveChanges();
        }

        public IEnumerable<Entreprise> EntrepriseParSecteur(string ville)
        {
            var req = from e in ctx.Entreprises.Where(c => c.Adresse.Ville.Equals(ville)).GroupBy(c => c.Secteur) select e;
             return (IEnumerable<Entreprise>) req.AsEnumerable();
        }

        public IEnumerable<Entreprise> FirstTwoEntreprise(string contrat)
        {
            var req = from e in ctx.Entreprises.OrderByDescending(c => c.Offres.Count) select e;
            return req.Take(2).AsEnumerable();
        }

        public IEnumerable<Entreprise> ListEntreprise()
        {
            return ctx.Entreprises.AsEnumerable();
        }

        public IEnumerable<Offre> ListOfferOfMonth()
        {
            var req = from o in ctx.Offres.Where(c => c.DatePublication.Month == DateTime.Now.Month) select o;
           return  req.AsEnumerable();
        }

        public IEnumerable<Offre> ListOffres()
        {
            return ctx.Offres.AsEnumerable();
        }

        public int NbEntreprisePME()
        {
            var req = from e in ctx.Entreprises.Where(c => (c.Effectif >= 10 && c.Effectif <= 250) && c.ChiffreAffaire < 5000) select e;
            return req.Count();
        }

        public Dictionary<string, int> NbOfferPost()
        {
            Dictionary<string, int> dict = new Dictionary<string, int>();
            var req = from p in ctx.Postulants select p;
            List<Postulant> postulants = req.ToList();
            foreach (Postulant p in postulants) {
                dict.Add(p.Nom, p.Offres.Count);
            }
            return dict;
        }
    }
}
