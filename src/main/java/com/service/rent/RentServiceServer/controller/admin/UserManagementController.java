package com.service.rent.RentServiceServer.controller.admin;

import com.service.rent.RentServiceServer.entity.Account;
import com.service.rent.RentServiceServer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/admin/users")
//@PreAuthorize("hasAnyAuthority('MODERATOR','ADMIN')")
public class UserManagementController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "")
    public List<Account> getAllAccounts(@RequestParam Integer pageNumber,
                                        @RequestParam Integer count,
                                        @RequestParam String q) {
        return StringUtils.isEmpty(q) ?
                accountService.getAccountsSortedStortedByMaklerProbabilityScoreDesc(pageNumber, count) :
                accountService.getAccountsSortedStortedByMaklerProbabilityScoreDesc(pageNumber, count, q);
    }

    @GetMapping(path = "/count")
    public Long getAccountCount(@RequestParam String q) {
        return StringUtils.isEmpty(q)?accountService.countAccounts():accountService.countAccounts(q);
    }

    @GetMapping(path = "/{userName}")
    public Account getAccount(@PathVariable String userName) {
        return accountService.getAccount(userName);
    }

    @PostMapping(path = "/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PostMapping(path = "/update")
    public Account updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @PostMapping(path = "/lock/{userName}")
    public Account lockAccount(@PathVariable String userName,
                               @RequestParam String lockReason) {
        return accountService.lockAccount(userName, lockReason);
    }

    @PostMapping(path = "/unlock/{userName}")
    public Account unlockAccount(@PathVariable String userName) {
        return accountService.unlockAccount(userName);
    }

    @PostMapping(path = "/delete")
    public Account deleteAccount(@RequestParam String userName,
                                 @RequestParam String reason) {
        return accountService.deleteAccount(userName, reason);
    }
}
