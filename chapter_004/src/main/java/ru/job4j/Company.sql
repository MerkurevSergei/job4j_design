Select person.name, company.name, company.id from person
left outer join company on (person.company_id = company.id)
where company.id IS DISTINCT FROM 5;

Select company.name, count(person.id) from company
left outer join person on (company.id = person.company_id)
group by company.name
order by count(person.id) desc
limit 1
