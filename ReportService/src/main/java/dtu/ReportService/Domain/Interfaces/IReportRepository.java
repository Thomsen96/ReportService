package dtu.ReportService.Domain.Interfaces;

import java.util.List;

import dtu.ReportService.Domain.Entities.Token;

import java.util.Collection;
import java.util.HashSet;


public interface IReportRepository {

	public HashSet<Token> get(String customerId);
	public Token create(String customerId);

	public HashSet<Token> getAll();
	public boolean delete(String customerId);
	public Token getVerfiedToken(String tokenUuid);
}
